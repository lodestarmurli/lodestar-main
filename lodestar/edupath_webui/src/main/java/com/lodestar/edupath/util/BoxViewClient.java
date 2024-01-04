package com.lodestar.edupath.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class BoxViewClient
{
	private static final Logger	OUT						= LoggerFactory.getLogger(BoxViewClient.class);

	private static final String	BOX_VIEW_SESSION_URL	= "https://view-api.box.com/1/sessions";

	private static final String	ACCEPT					= "Accept";
	private static final String	JSON_CONTENT_TYPE		= "application/json";
	private static final String	AUTHORIZATION			= "Authorization";
	private static final String	BOX_VIEW_API_KEY		= "Token anwlrkl14qkh60a11kobd8ofid8zm0gs";

	private static final String	ASSETS					= "assets";
	private static final String	URLS					= "urls";

	public String getSessionURL(String documentId) throws ClientProtocolException, IOException
	{

		try
		{

			//Start Sasedeve Edited By Mrutyunjaya on Date 02-01-2018
			
			/*JSONObject jsonObject = null;
			if (documentId != null && !documentId.isEmpty())
			{
				StringBuilder builder = new StringBuilder();
				StringBuilder boxViewurl = new StringBuilder();
				boxViewurl.append(BOX_VIEW_SESSION_URL);
				OUT.debug("getting Sesson from uri: {} for documentId: {}", boxViewurl.toString(), documentId);
				try (CloseableHttpClient httpclient = HttpClientBuilder.create().build();)
				{
					HttpPost post = new HttpPost(boxViewurl.toString());
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("document_id", documentId);
					jsonObj.put("duration", 120);
					StringEntity entity = new StringEntity(jsonObj.toString());
					post.setEntity(entity);
					post.setHeader(ACCEPT, JSON_CONTENT_TYPE);
					post.setHeader(AUTHORIZATION, BOX_VIEW_API_KEY);
					post.setHeader("Content-Type", JSON_CONTENT_TYPE);

					OUT.debug("Headers {} ", Arrays.toString(post.getAllHeaders()));
					HttpResponse response = httpclient.execute(post);

					OUT.debug("Response status code:{}", response.getStatusLine().getStatusCode());
					try (InputStreamReader in = new InputStreamReader(response.getEntity().getContent()); BufferedReader bufferedReader = new BufferedReader(in);)
					{
						String lineString;
						while ((lineString = bufferedReader.readLine()) != null)
						{
							builder.append(lineString);
						}
						if (builder.toString().length() > 2)
						{
							jsonObject = new JSONObject(builder.toString());
						}
					}
					catch (Exception e)
					{
						OUT.error(ApplicationConstants.EXCEPTION, e);
					}

					OUT.debug("Session Creation Result: {}", jsonObject);

				}
				catch (Exception e)
				{
					OUT.error(ApplicationConstants.EXCEPTION, e);
				}
			}
			return jsonObject.getJSONObject(URLS).getString(ASSETS);*/
			
			return documentId;
			
			
			//End Sasedeve Edited By Mrutyunjaya on Date 02-01-2018
			
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

		return null;
	}

	public static void main(String[] args) throws ClientProtocolException, IOException
	{
		new BoxViewClient().getSessionURL("dc2f568022f449fbba22029eff805fa6");

	}
	}
