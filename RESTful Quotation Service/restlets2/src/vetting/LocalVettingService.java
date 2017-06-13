package vetting;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.restlet.Request;

import com.google.gson.Gson;

import core.ClientInfo;

public class LocalVettingService implements VettingService {
	Map<String, Integer> pointsDB = new HashMap<String, Integer>();

	{
		pointsDB.put("PQR254/1", 0);
		pointsDB.put("ABC123/4", 0);
		pointsDB.put("XYZ567/9", 5);

	}

	public boolean vetClient(ClientInfo info) {
		Integer value = pointsDB.get(info.licenseNumber);
		return value != null && (value == info.points);
	}

}
