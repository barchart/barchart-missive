package com.barchart.missive.api.example.hocon;

import java.io.InputStream;

import com.barchart.missive.api.Manifest;
import com.barchart.missive.api.Missive;
import com.barchart.missive.api.Tag;

public final class NetSpec {

	public static enum ConnectionType {
		MULTI_READER, MULTI_WRITER
	};
	
	public static final Tag<String> Description = new Tag<String>("Description", String.class);
	public static final Tag<String> RemoteAddress = new Tag<String>("RemoteAddress", String.class);
	public static final Tag<String> LocalAddress = new Tag<String>("LocalAddress", String.class);
	public static final Tag<ConnectionType> Pipeline = new Tag<ConnectionType>("Pipeline", ConnectionType.class);
	
	public static final Manifest Network = new Manifest("Network", new Tag<?>[] {
		Description, RemoteAddress, LocalAddress, Pipeline
	});
	
	public static final Tag<Missive[]> Networks = new Tag<Missive[]>("Networks", Network);
	
	public static final Tag<String> Schedule = new Tag<String>("Schedule", String.class);
	public static final Tag<String> Testing = new Tag<String>("Testing", String.class);
	
	public static enum TType {
		
		CME("barchart.translator.cme"), CQFN("barchart.translator.cfn"),
		NYM("barchart.translator.nyl"), ICE("barchart.translator.ice"),
		CQG("barchart.translator.cqg"), CMESTR("barchart.translator.cmestr"),
		NETTY("barchart.translator.netty4");
		
		public final String name; 
		
		private TType(final String name) {
			this.name = name;
		}
		
	};
	
	public static final Tag<TType> TranslatorType = new Tag<TType>("TranslatorType", TType.class);
	public static final Tag<String> TranslatorID = new Tag<String>("TranslatorID", String.class);
	public static final Tag<Integer> ChannelID = new Tag<Integer>("ChannelID", Integer.class);
	public static final Tag<Missive[]> Sources = new Tag<Missive[]>("Sources", Network);
	public static final Tag<Missive[]> Destinations = new Tag<Missive[]>("Destinations", Network);
	public static final Tag<InputStream> Templates = new Tag<InputStream>("Templates", InputStream.class);
	public static final Tag<Integer> CustomProperty = new Tag<Integer>("CustomProperty", Integer.class);
			
	public static final Manifest NetworkConfig = new Manifest("NetworkConfig", new Tag<?>[]{
		TranslatorType, TranslatorID, ChannelID, Sources,
		Destinations, Templates, CustomProperty
	});
	
}
