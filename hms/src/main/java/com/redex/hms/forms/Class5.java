package com.redex.hms.forms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.redex.hms.configs.core.Injector;
import com.redex.hms.models.Employee;
import com.redex.hms.service.EmployeeService;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

@ContextConfiguration(locations = { "/WEB-INF/application-context.xml" })
public class Class5 extends CustomComponent implements View{

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_1;
	@AutoGenerated
	private Button btnSave;
	@AutoGenerated
	private TextField txtName;
	@AutoGenerated
	private Label lblName;
	@AutoGenerated
	private Label lbleading;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	@Autowired
	private EmployeeService employeeService;


	public Class5(final HttpServletRequest httpServletRequest) {
		Injector.inject(this,httpServletRequest);
		buildMainLayout();
		setCompositionRoot(mainLayout);
		lbleading.setStyleName(Runo.LABEL_H1);
		lbleading.setValue("This is Beverage");
		addListeners();
	}

	private void addListeners() {
		btnSave.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(final ClickEvent event) {
				try {
					Notification.show("@Calssdl");
					String empName=txtName.getValue();
					Employee employee=new Employee();
					employee.setName(empName);
					/*if(employeeService==null){
						ApplicationContext appContext =
								new ClassPathXmlApplicationContext("application-context.xml");
						employeeService=(EmployeeService) appContext.getBean("employeeService");
						System.out.println("emplsoyee service is null");
					}*/
					employeeService.save(employee);
					//new Helper().test();
					Notification.show("Saved successfully");

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Notification.show("Some Error sss","mess: "+e.getMessage(), Notification.Type.ERROR_MESSAGE);
				}

			}
		});

	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// lbleading
		lbleading = new Label();
		lbleading.setImmediate(false);
		lbleading.setWidth("-1px");
		lbleading.setHeight("-1px");
		lbleading.setValue("Add Employee");
		mainLayout.addComponent(lbleading);

		// absoluteLayout_1
		absoluteLayout_1 = buildAbsoluteLayout_1();
		mainLayout.addComponent(absoluteLayout_1);

		return mainLayout;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_1() {
		// common part: create layout
		absoluteLayout_1 = new AbsoluteLayout();
		absoluteLayout_1.setImmediate(false);
		absoluteLayout_1.setWidth("300px");
		absoluteLayout_1.setHeight("120px");

		// lblName
		lblName = new Label();
		lblName.setImmediate(false);
		lblName.setWidth("-1px");
		lblName.setHeight("-1px");
		lblName.setValue("Name");
		absoluteLayout_1.addComponent(lblName, "top:20.0px;left:20.0px;");

		// txtName
		txtName = new TextField();
		txtName.setImmediate(false);
		txtName.setWidth("177px");
		txtName.setHeight("-1px");
		absoluteLayout_1.addComponent(txtName, "top:20.0px;left:83.0px;");

		// btnSave
		btnSave = new Button();
		btnSave.setCaption("Button");
		btnSave.setImmediate(true);
		btnSave.setWidth("-1px");
		btnSave.setHeight("-1px");
		absoluteLayout_1.addComponent(btnSave, "top:74.0px;left:83.0px;");

		return absoluteLayout_1;
	}

	@Override
	public void enter(final ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
