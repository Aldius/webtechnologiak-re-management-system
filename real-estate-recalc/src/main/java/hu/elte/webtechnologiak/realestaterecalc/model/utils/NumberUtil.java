package hu.elte.webtechnologiak.realestaterecalc.model.utils;

public class NumberUtil {

	public static double zero(final Double incoming) {
		if (incoming == null) {
			return 0;
		} else {
			return incoming;
		}
	}

}
