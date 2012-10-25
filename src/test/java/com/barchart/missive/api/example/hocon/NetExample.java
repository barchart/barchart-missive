package com.barchart.missive.api.example.hocon;

import static com.barchart.missive.api.example.hocon.NetSpec.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.barchart.missive.api.Missive;
import com.barchart.missive.api.MissiveException;
import com.barchart.missive.api.example.hocon.NetSpec.ConnectionType;
import com.barchart.missive.api.example.hocon.NetSpec.TType;

public class NetExample {

	public static void main(final String[] args) throws MissiveException, FileNotFoundException {
		
		/* The first part would be handled by some translator, so you wouldn't need to do this */
		
		/* Build network missives */
		final Missive CME_DATA_DEV = new Missive(Network);
		CME_DATA_DEV.set(Description, "Test data for CME");
		CME_DATA_DEV.set(RemoteAddress, "225.1.1.1:11001");
		CME_DATA_DEV.set(LocalAddress, "testlan");
		CME_DATA_DEV.set(Pipeline, ConnectionType.MULTI_READER);
		
		final Missive CME_DATA_DEV_BUF = new Missive(Network);
		CME_DATA_DEV_BUF.set(Description, "Test data for CME");
		CME_DATA_DEV_BUF.set(RemoteAddress, "225.1.1.1:11001");
		CME_DATA_DEV_BUF.set(LocalAddress, "testlan");
		CME_DATA_DEV_BUF.set(Pipeline, ConnectionType.MULTI_READER);
		
		/* Build missive from manifest */
		final Missive config = new Missive(NetworkConfig);
		
		config.set(TranslatorType, TType.CME);
		config.set(TranslatorID, "barchart.translator.cmestr-channel:100");
		config.set(ChannelID, 100);
		config.set(Sources, new Missive[]{CME_DATA_DEV});
		config.set(Destinations, new Missive[]{CME_DATA_DEV_BUF});
		config.set(Templates, new FileInputStream("/tmp/cme-templates.xml"));
		config.set(CustomProperty, 4);
		
		/* So instead of getting a Config object, you would get a Missive */
		
		final TType myType = config.get(TranslatorType);
		final String myID = config.get(TranslatorID);
		final int myChannelID = config.get(ChannelID);
		
		final Missive[] mySources = config.get(Sources);
		final Missive mySource = mySources[0];
		final String mySourceDescription = mySource.get(Description);
		final ConnectionType mySourceTpe = mySource.get(Pipeline);
		
		/* etc... */
		
		
	}
	
	/* Example Typing Missive */
	public class NetConfig extends Missive {
		public NetConfig() {
			super(NetSpec.NetworkConfig);
		}
	}
}
