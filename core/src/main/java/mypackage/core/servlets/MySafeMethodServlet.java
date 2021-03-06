package mypackage.core.servlets;

import java.io.IOException;

import javax.jcr.Repository;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

// TODO: Auto-generated Javadoc
/**
 * The Class MySafeMethodServlet.
 * 
 * http://localhost:4502/content/mytrainingprojecct/en.data.html
 */
@SlingServlet(resourceTypes = "mytrainingproject/components/structure/page", selectors = "data", methods = "GET")
public class MySafeMethodServlet extends SlingSafeMethodsServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3960692666512058118L;

	/** The repository. */
	@Reference
	private Repository repository;

	/* (non-Javadoc)
	 * @see org.apache.sling.api.servlets.SlingSafeMethodsServlet#doGet(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)
	 */
	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {

		response.setHeader("Content-Type", "application/json");

		// response.getWriter().print("{\"coming\" : \"soon\"}");

		String[] keys = repository.getDescriptorKeys();

		JSONObject jsonObject = new JSONObject();

		for (int i = 0; i < keys.length; i++) {

			try {

				jsonObject.put(keys[i], repository.getDescriptor(keys[i]));

			}

			catch (JSONException e) {

				e.printStackTrace();

			}

		}

		response.getWriter().print(jsonObject.toString());

	}

}
