package com.barchart.missive.api;

import java.util.Hashtable;
import java.util.Map.Entry;

import javax.naming.Context;
import javax.naming.InitialContext;

public class TestNaming {
	
	public static void main(final String[] args) throws Exception {
		
		
		
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
		env.put(Context.PROVIDER_URL, "rmi://localhost:1099");
		Context ctx = new InitialContext(env);
		
		for(final Entry<?,?> e : ctx.getEnvironment().entrySet()) {
			System.out.println(e.getKey() + " : " + e.getValue());
		}
		
	}

}
