package legacy.missive;

import static legacy.example.FixSpec.*;

import com.barchart.missive.core.Tag;

public class Instrument extends FIXMessage {

	public Instrument() {
		include(new Tag<?>[]{
			Symbol,
			Exchange,
			MaturityDate
		});
	}
	
}
