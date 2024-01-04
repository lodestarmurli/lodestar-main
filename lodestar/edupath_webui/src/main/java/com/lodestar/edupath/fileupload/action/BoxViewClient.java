package com.lodestar.edupath.fileupload.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.bulkupload.EActionStatus;
import com.lodestar.edupath.dataaccessobject.dao.document.DocumentsDAO;
import com.lodestar.edupath.dataaccessobject.dao.occupation.OccupationDAO;
import com.lodestar.edupath.datatransferobject.dto.DocumentDTO;
import com.lodestar.edupath.datatransferobject.dto.occupation.OccupationDTO;
import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class BoxViewClient
{
	private static final Logger	OUT						= LoggerFactory.getLogger(BoxViewClient.class);

	private static final String	BOX_VIEW_SESSION_URL	= "https://view-api.box.com/1/sessions";
	private static final String	BOX_VIEW_UPLOAD			= "https://upload.view-api.box.com/1/documents";

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

			JSONObject jsonObject = null;
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
			return jsonObject.getJSONObject(URLS).getString(ASSETS);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

		return null;
	}

	private static HttpClient getHttpClient()
	{
		RequestConfig config = RequestConfig.custom().setConnectTimeout(10 * 1000).setConnectionRequestTimeout(5 * 60 * 1000).setSocketTimeout(5 * 60 * 1000)
				.build();
		return HttpClientBuilder.create().setDefaultRequestConfig(config).build();
	}

	public JSONObject uploadFileToBoxView(String documentPath, String documentName)
	{
		JSONObject jsonObject = null;
		try
		{
			if (documentPath != null && !documentPath.isEmpty())
			{
				StringBuilder builder = new StringBuilder();
				StringBuilder boxViewurl = new StringBuilder();
				boxViewurl.append(BOX_VIEW_UPLOAD);
				OUT.debug("Uploading the file to uri: {} for document: {}", boxViewurl.toString(), documentPath);
				try
				{
					HttpClient httpclient = getHttpClient();
					HttpPost post = new HttpPost(boxViewurl.toString());
					JSONObject jsonObj = new JSONObject();

					String boundary = "----------------fileabxbcs";
					StringEntity entity = new StringEntity(jsonObj.toString());
					post.setEntity(entity);
					// post.setHeader(ACCEPT, JSON_CONTENT_TYPE);
					post.setHeader(AUTHORIZATION, BOX_VIEW_API_KEY);
					post.setHeader("Content-Type", "multipart/form-data;boundary=" + boundary);

					MultipartEntityBuilder mpeBuilder = MultipartEntityBuilder.create();
					mpeBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
					File file = new File(documentPath);
					// InputStream stream = new FileInputStream(file);
					// mpeBuilder.addBinaryBody("file", stream, ContentType.DEFAULT_BINARY, "");
					mpeBuilder.setBoundary(boundary);
					mpeBuilder.addBinaryBody("file", file);
					mpeBuilder.addTextBody("non_svg", "true");
					mpeBuilder.addTextBody("name", documentName);

					post.setEntity(mpeBuilder.build());

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

					OUT.debug("File Upload Result: {}", jsonObject);

				}
				catch (Exception e)
				{
					OUT.error(ApplicationConstants.EXCEPTION, e);
				}
			}

		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

		return jsonObject;
	}

	public static void main(String[] args) throws ClientProtocolException, IOException
	{
		BoxViewClient boxViewClient = new BoxViewClient();
		// new BoxViewClient().getSessionURL("dc2f568022f449fbba22029eff805fa6");
		// String fileName = "Occupation report - treasury manager.pptx";
		// String occupationName = "OccupationName";
		List<OccupationDTO> occList;
		Set<Integer> occupationIdSet = null;
		try
		{
			occupationIdSet = boxViewClient.getIdSet();
			occList = new OccupationDAO().getOccupationDetails();
			OUT.debug("{}", occupationIdSet);
			if (null != occList && !occList.isEmpty())
			{
				EActionStatus eaStatus = EActionStatus.FAILURE;
				for (OccupationDTO occupationDTO : occList)
				{
					if(!occupationIdSet.contains(occupationDTO.getId()))
					{
						JSONObject uploadFileToBoxView = boxViewClient.uploadFileToBoxView(
								"D:/development/lodestar/boxview_document/occupation_report/" + occupationDTO.getName() + ".pptx", occupationDTO.getName());
						if (null != uploadFileToBoxView && uploadFileToBoxView.has("id") )
						{
							OUT.debug("{}, {}, {}", occupationIdSet, occupationDTO.getId(), occupationIdSet.contains(occupationDTO.getId()));
							 String documentPath = uploadFileToBoxView.getString("id");
							 eaStatus = boxViewClient.insertDocumentPath(occupationDTO, documentPath);
							 OUT.debug("{} for upload file and insert document status {}", occupationDTO.getName(), eaStatus);
						}
					}
				}
			}

		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}

	}

	private Set<Integer> getIdSet()
	{
		Set<Integer> occupationIdSet = null;
		List<DocumentDTO> docList = null;
		try
		{
			docList = new DocumentsDAO().getDocumentList();
			if (null != docList && !docList.isEmpty())
			{
				occupationIdSet = new HashSet<Integer>();
				for (DocumentDTO documentDTO : docList)
				{
					occupationIdSet.add(documentDTO.getOccupationId());
				}
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return occupationIdSet;
	}

	private EActionStatus insertDocumentPath(OccupationDTO occupationDTO, String documentPath)
	{
		try
		{
			Integer count = -1;
			OUT.debug("Document Path id {}", documentPath);
			count = new DocumentsDAO().insertDocumentPath(occupationDTO, documentPath);
			if (count > 0)
			{
				return EActionStatus.SUCCESS;
			}
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
		}
		return EActionStatus.FAILURE;
	}
}
