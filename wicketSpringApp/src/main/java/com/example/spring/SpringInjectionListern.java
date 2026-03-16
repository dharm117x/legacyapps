package com.example.spring;

import org.apache.wicket.Component;
import org.apache.wicket.application.IComponentInstantiationListener;

import com.example.StartUpApplcation;

public class SpringInjectionListern implements IComponentInstantiationListener {

	public SpringInjectionListern(StartUpApplcation startUpApplcation) {
		System.out.println("SpringInjectionListern.SpringInjectionListern()");
	}

	@Override
	public void onInstantiation(Component component) {
		System.out.println("SpringInjectionListern.onInstantiation()");
	}

}
