package com.redex.hms.forms;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.eventbus.Subscribe;
import com.redex.hms.beans.MenuDetailsBean;
import com.redex.hms.business.service.MenuDetailsBusinessService;
import com.redex.hms.configs.core.Injector;
import com.redex.hms.forms.domain.User;
import com.redex.hms.forms.event.DashboardEventBus;
import com.redex.hms.forms.event.DashboardEvent.BrowserResizeEvent;
import com.redex.hms.forms.event.DashboardEvent.CloseOpenWindowsEvent;
import com.redex.hms.forms.event.DashboardEvent.UserLoggedOutEvent;
import com.redex.hms.forms.event.DashboardEvent.UserLoginRequestedEvent;
import com.redex.hms.forms.menu.RedexViewItem;
import com.redex.hms.utils.RedexUtils;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.Page;
import com.vaadin.server.Page.BrowserWindowResizeEvent;
import com.vaadin.server.Page.BrowserWindowResizeListener;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@Theme("dashboard")
@Widgetset("com.vaadin.demo.dashboard.DashboardWidgetSet")
@Title("HMS")
@SuppressWarnings("serial")
public final class DashboardUI extends UI {

	@Autowired
	private MenuDetailsBusinessService menuDetailsBusinessService;

	HttpServletRequest httpServletRequest=null;
	/*
	 * This field stores an access to the dummy backend layer. In real
	 * applications you most likely gain access to your beans trough lookup or
	 * injection; and not in the UI but somewhere closer to where they're
	 * actually accessed.
	 */
	private final DashboardEventBus dashboardEventbus = new DashboardEventBus();

	private    HashMap<String, RedexViewItem> redexMenuItemsMap=null;

	@Override
	protected void init(final VaadinRequest request) {
		setLocale(Locale.US);
		httpServletRequest=RedexUtils.getHttpServletRequest(request);
		Injector.inject(getUI(), httpServletRequest);
		DashboardEventBus.register(this);
		Responsive.makeResponsive(this);
		addStyleName(ValoTheme.UI_WITH_MENU);
		loadMenuItems();
		updateContent();

		// Some views need to be aware of browser resize events so a
		// BrowserResizeEvent gets fired to the event bus on every occasion.
		Page.getCurrent().addBrowserWindowResizeListener(
				new BrowserWindowResizeListener() {
					@Override
					public void browserWindowResized(
							final BrowserWindowResizeEvent event) {
						DashboardEventBus.post(new BrowserResizeEvent());
					}
				});
	}

	private void loadMenuItems() {
		List<MenuDetailsBean> menuDetailsBeans=menuDetailsBusinessService.getMenuDetailsForUser(null);

		redexMenuItemsMap=new HashMap<String, RedexViewItem>();
		for(MenuDetailsBean menuDetailsBean: menuDetailsBeans){
			redexMenuItemsMap.put(menuDetailsBean.getViewId(),new RedexViewItem(menuDetailsBean,httpServletRequest));
		}

	}



	/**
	 * Updates the correct content for this UI based on the current user status.
	 * If the user is logged in with appropriate privileges, main view is shown.
	 * Otherwise login view is shown.
	 */
	private void updateContent() {
		User user = (User) VaadinSession.getCurrent().getAttribute(
				User.class.getName());
		if (user != null && "admin".equals(user.getRole())) {
			// Authenticated user
			setContent(new MainView());
			removeStyleName("loginview");
			getNavigator().navigateTo(getNavigator().getState());
		} else {
			setContent(new LoginView());
			addStyleName("loginview");
		}
	}

	@Subscribe
	public void userLoginRequested(final UserLoginRequestedEvent event) {
		User user =new User(event.getUserName(),
				event.getPassword());
		user.setRole("admin");
		/*getDataProvider().authenticate(event.getUserName(),
				event.getPassword());*/
		//TODO authentication
		VaadinSession.getCurrent().setAttribute(User.class.getName(), user);
		updateContent();

	}

	@Subscribe
	public void userLoggedOut(final UserLoggedOutEvent event) {
		// When the user logs out, current VaadinSession gets closed and the
		// page gets reloaded on the login screen. Do notice the this doesn't
		// invalidate the current HttpSession.
		VaadinSession.getCurrent().close();
		Page.getCurrent().reload();
	}

	@Subscribe
	public void closeOpenWindows(final CloseOpenWindowsEvent event) {
		for (Window window : getWindows()) {
			window.close();
		}
	}



	public static DashboardEventBus getDashboardEventbus() {
		return ((DashboardUI) getCurrent()).dashboardEventbus;
	}
	public static Map<String, RedexViewItem> getRedexViewItemMap() {
		return ((DashboardUI) getCurrent()).redexMenuItemsMap;
	}
	public static HttpServletRequest getHttpServletRequest() {
		return ((DashboardUI) getCurrent()).httpServletRequest;
	}

}
