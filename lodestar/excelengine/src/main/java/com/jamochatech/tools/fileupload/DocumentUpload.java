package com.jamochatech.tools.fileupload;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jamochatech.tools.fileupload.dao.DocumentDAO;
import com.jamochatech.tools.fileupload.dao.OccupationDAO;
import com.jamochatech.tools.fileupload.dto.DocumentDTO;
import com.jamochatech.tools.fileupload.dto.IndustryDTO;
import com.jamochatech.tools.fileupload.dto.OccupationDTO;
import com.jamochatech.tools.fileupload.dto.SubjectDTO;

public class DocumentUpload
{
	public static final Logger		OUT					= LoggerFactory.getLogger(DocumentUpload.class);

	private static DocumentUpload	INSTANCE			= new DocumentUpload();

	private static final String		BOX_VIEW_UPLOAD		= "https://upload.view-api.box.com/1/documents";
	private static final String		BOX_VIEW_DELETE		= "https://view-api.box.com/1/documents";
	private static final String		AUTHORIZATION		= "Authorization";
	private static final String		BOX_VIEW_API_KEY	= "Token anwlrkl14qkh60a11kobd8ofid8zm0gs";

	private DocumentUpload()
	{
		// Do Nothing
	}

	public static DocumentUpload getInstance()
	{
		return INSTANCE;
	}

	private static HttpClient getHttpClient()
	{
		RequestConfig config = RequestConfig.custom().setConnectTimeout(10 * 1000).setConnectionRequestTimeout(5 * 60 * 1000).setSocketTimeout(5 * 60 * 1000)
				.build();
		return HttpClientBuilder.create().setDefaultRequestConfig(config).build();
	}

	/*
	 * private static String getSessionURL(String documentId) throws ClientProtocolException, IOException
	 * {
	 * try
	 * {
	 * String filePath;
	 * String fileName;
	 * String sheetName;
	 * String documentPath;
	 * String BOX_VIEW_SESSION_URL = "https://view-api.box.com/1/sessions";
	 * String ACCEPT = "Accept";
	 * String JSON_CONTENT_TYPE = "application/json";
	 * String ASSETS = "assets";
	 * String URLS = "urls";
	 * JSONObject jsonObject = null;
	 * if (documentId != null && !documentId.isEmpty())
	 * {
	 * StringBuilder builder = new StringBuilder();
	 * StringBuilder boxViewurl = new StringBuilder();
	 * boxViewurl.append(BOX_VIEW_SESSION_URL);
	 * OUT.debug("getting Sesson from uri: {} for documentId: {}", boxViewurl.toString(), documentId);
	 * try (CloseableHttpClient httpclient = HttpClientBuilder.create().build();)
	 * {
	 * HttpPost post = new HttpPost(boxViewurl.toString());
	 * JSONObject jsonObj = new JSONObject();
	 * jsonObj.put("document_id", documentId);
	 * jsonObj.put("duration", 120);
	 * StringEntity entity = new StringEntity(jsonObj.toString());
	 * post.setEntity(entity);
	 * post.setHeader(ACCEPT, JSON_CONTENT_TYPE);
	 * post.setHeader(AUTHORIZATION, BOX_VIEW_API_KEY);
	 * post.setHeader("Content-Type", JSON_CONTENT_TYPE);
	 * 
	 * OUT.debug("Headers {} ", Arrays.toString(post.getAllHeaders()));
	 * HttpResponse response = httpclient.execute(post);
	 * 
	 * OUT.debug("Response status code:{}", response.getStatusLine().getStatusCode());
	 * try (InputStreamReader in = new InputStreamReader(response.getEntity().getContent()); BufferedReader bufferedReader = new BufferedReader(in);)
	 * {
	 * String lineString;
	 * while ((lineString = bufferedReader.readLine()) != null)
	 * {
	 * builder.append(lineString);
	 * }
	 * if (builder.toString().length() > 2)
	 * {
	 * jsonObject = new JSONObject(builder.toString());
	 * }
	 * }
	 * catch (Exception e)
	 * {
	 * OUT.error("Exception", e);
	 * }
	 * 
	 * OUT.debug("Session Creation Result: {}", jsonObject);
	 * 
	 * }
	 * catch (Exception e)
	 * {
	 * OUT.error("Exception", e);
	 * }
	 * }
	 * return jsonObject.getJSONObject(URLS).getString(ASSETS);
	 * }
	 * catch (Exception e)
	 * {
	 * OUT.error("Exception", e);
	 * }
	 * return null;
	 * }
	 */

	private static JSONObject uploadFileToBoxView(String documentPath, String documentName, String documentId)
	{
		JSONObject jsonObject = null;
		try
		{
			if (documentPath != null && !documentPath.isEmpty())
			{
				try
				{
					StringBuilder builder = new StringBuilder();
					StringBuilder boxViewurl = new StringBuilder();
					if (null != documentId && "" != documentId)
					{
						boxViewurl.append(BOX_VIEW_DELETE).append("/").append(documentId);
						OUT.debug("Deleting existing file to uri: {}", boxViewurl.toString());
						HttpClient httpclient = getHttpClient();
						HttpDelete docDelete = new HttpDelete(boxViewurl.toString());
						docDelete.setHeader(AUTHORIZATION, BOX_VIEW_API_KEY);
						HttpResponse response = httpclient.execute(docDelete);

						OUT.debug("Response status code:{}", response.getStatusLine().getStatusCode());
					}
					builder = new StringBuilder();
					boxViewurl = new StringBuilder();
					boxViewurl.append(BOX_VIEW_UPLOAD);

					OUT.debug("Uploading the file to uri: {} for document: {}", boxViewurl.toString(), documentPath);

					HttpClient httpclient = getHttpClient();
					HttpPost post = new HttpPost(boxViewurl.toString());

					JSONObject jsonObj = new JSONObject();

					String boundary = "----------------fileabxbcs";
					StringEntity entity = new StringEntity(jsonObj.toString());
					post.setEntity(entity);
					post.setHeader(AUTHORIZATION, BOX_VIEW_API_KEY);
					post.setHeader("Content-Type", "multipart/form-data;boundary=" + boundary);

					MultipartEntityBuilder mpeBuilder = MultipartEntityBuilder.create();
					mpeBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
					File file = new File(documentPath);
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
						OUT.error("Exception", e);
					}
					OUT.debug("File Upload Result: {}", jsonObject);
				}
				catch (Exception e)
				{
					OUT.error("Exception", e);
				}
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}

		return jsonObject;
	}

	private static void parseExcel(String excelFilePath, String sheetName, String documentPath)
	{
		Workbook wb = null;
		try
		{
			List<OccupationDTO> occupationList = getOccupationList();
			List<IndustryDTO> industryList = getIndustryList();
			List<SubjectDTO> subjectList = getSubjectList();
			Map<Integer, DocumentDTO> occupationMap = null;
			Map<Integer, DocumentDTO> industrytMap = null;
			Map<Integer, DocumentDTO> subjectMap = null;

			List<DocumentDTO> docList = new DocumentDAO().getDocumentList();
			if (null != docList && !docList.isEmpty())
			{
				occupationMap = new HashMap<Integer, DocumentDTO>();
				industrytMap = new HashMap<Integer, DocumentDTO>();
				subjectMap = new HashMap<Integer, DocumentDTO>();
				for (DocumentDTO documentDTO : docList)
				{
					if (documentDTO.getOccupationId() != null)
					{
						occupationMap.put(documentDTO.getOccupationId(), documentDTO);
					}
					if (documentDTO.getIndustryId() != null)
					{
						industrytMap.put(documentDTO.getIndustryId(), documentDTO);
					}
					if (documentDTO.getSubjectId() != null)
					{
						subjectMap.put(documentDTO.getSubjectId(), documentDTO);
					}
				}
			}
			wb = WorkbookFactory.create(new File(excelFilePath));
			;
			Sheet xlSheet = wb.getSheet(sheetName);

			int firstRow = xlSheet.getFirstRowNum();
			int lastRow = xlSheet.getLastRowNum();
			String name;
			String type;
			String documentName = "";
			File documentFile = null;
			int occupationId = 0, industryId = 0, subjectId = 0, insertStatus = 0;
			boolean uploadStatus = false;
			DocumentDTO documentDTO = null;

			for (int rowIndex = firstRow + 1; rowIndex <= lastRow; rowIndex++)
			{
				try
				{
					occupationId = 0;
					industryId = 0;
					subjectId = 0;
					uploadStatus = false;
					insertStatus = 0;

					Row row = xlSheet.getRow(rowIndex);
					name = row.getCell(0).toString().trim();
					type = row.getCell(1).toString().trim();
					documentName = row.getCell(2).toString().trim();

					if (documentName != null && !documentName.isEmpty() && name != null && !name.isEmpty() && type != null && !type.isEmpty())
					{
						documentFile = new File(documentPath + File.separator + documentName);

						if (documentFile.exists() && !documentFile.isDirectory())
						{
							if (type.equalsIgnoreCase("occupation"))
							{
								if (occupationList != null && !occupationList.isEmpty())
								{
									for (OccupationDTO occupationDTO : occupationList)
									{
										if (occupationDTO.getName().trim().equalsIgnoreCase(name))
										{
											occupationId = occupationDTO.getId();
											if (occupationMap != null)
											{
												documentDTO = occupationMap.get(occupationId);
											}

											if (null != documentDTO)
											{
												OUT.debug("STARTing upload for occupation: {}, occupation id: {} and documentName: {}", occupationDTO.getName(),
														occupationDTO.getId(), documentName);
												JSONObject uploadFileToBoxView = uploadFileToBoxView(documentPath + File.separator + documentName, name,
														documentDTO.getDocumentPath());

												if (null != uploadFileToBoxView)
												{
													String docPath = uploadFileToBoxView.getString("id");

													uploadStatus = new DocumentDAO().updateDocumentPath(occupationDTO.getId(), docPath, type);
													OUT.debug("{} for occupation upload file and update document status {}", occupationDTO.getName(), uploadStatus);
												}
												else
												{
													OUT.error("NULLResponse-for occupation update from boxview: DocumentName: {}, occupationId: {}", documentName,
															occupationId);
												}
												try
												{
													Thread.sleep(30000);
												}
												catch (Exception e)
												{

												}
											}
											else
											{
												JSONObject uploadFileToBoxView = uploadFileToBoxView(documentPath + File.separator + documentName, name, null);
												if (null != uploadFileToBoxView)
												{
													String docPath = uploadFileToBoxView.getString("id");

													insertStatus = new DocumentDAO().insertDocumentPath(occupationDTO.getId(), docPath, type);
													OUT.debug("{} for occupation upload file and insert document status {}", occupationDTO.getName(), insertStatus);
												}
												else
												{
													OUT.error("NULLResponse-for occupation insert from boxview: DocumentName: {}, occupationId: {}", documentName,
															occupationId);
												}
												try
												{
													Thread.sleep(30000);
												}
												catch (Exception e)
												{

												}
											}
										}
									}
									if (occupationId == 0)
									{
										OUT.error("name {} of type {} not found in the database", name, type);
									}
								}
							}
							else if (type.equalsIgnoreCase("industry"))
							{
								if (industryList != null && !industryList.isEmpty())
								{
									for (IndustryDTO industryDTO : industryList)
									{
										if (industryDTO.getName().trim().equalsIgnoreCase(name))
										{
											industryId = industryDTO.getId();
											if (subjectMap != null)
											{
												documentDTO = subjectMap.get(industryId);
											}

											if (null != documentDTO)
											{
												JSONObject uploadFileToBoxView = uploadFileToBoxView(documentPath + File.separator + documentName, name,
														documentDTO.getDocumentPath());

												if (null != uploadFileToBoxView)
												{
													String docPath = uploadFileToBoxView.getString("id");

													uploadStatus = new DocumentDAO().updateDocumentPath(industryDTO.getId(), docPath, type);
													OUT.debug("{} for industry upload file and update document status {}", industryDTO.getName(), uploadStatus);
												}
												else
												{
													OUT.error("NULLResponse-for industry update from boxview: DocumentName: {}, industry: {}", documentName,
															industryId);
												}
												try
												{
													Thread.sleep(30000);
												}
												catch (Exception e)
												{

												}
											}
											else
											{
												JSONObject uploadFileToBoxView = uploadFileToBoxView(documentPath + File.separator + documentName, name, null);
												if (null != uploadFileToBoxView)
												{
													String docPath = uploadFileToBoxView.getString("id");

													insertStatus = new DocumentDAO().insertDocumentPath(industryDTO.getId(), docPath, type);
													OUT.debug("{} for industry upload file and insert document status {}", industryDTO.getName(), insertStatus);
												}
												else
												{
													OUT.error("NULLResponse-for industry insert from boxview: DocumentName: {}, industry: {}", documentName,
															industryId);
												}
												try
												{
													Thread.sleep(30000);
												}
												catch (Exception e)
												{

												}
											}
										}
									}
									if (industryId == 0)
									{
										OUT.error("name {} of type {} not found in the database", name, type);
									}
								}
							}
							else if (type.equalsIgnoreCase("subject"))
							{
								if (subjectList != null && !subjectList.isEmpty())
								{
									for (SubjectDTO subjectDTO : subjectList)
									{
										if (subjectDTO.getName().trim().equalsIgnoreCase(name))
										{
											subjectId = subjectDTO.getId();

											if (subjectMap != null)
											{
												documentDTO = subjectMap.get(subjectId);
											}

											if (null != documentDTO)
											{
												JSONObject uploadFileToBoxView = uploadFileToBoxView(documentPath + File.separator + documentName, name,
														documentDTO.getDocumentPath());
												if (null != uploadFileToBoxView)
												{
													String docPath = uploadFileToBoxView.getString("id");

													uploadStatus = new DocumentDAO().updateDocumentPath(subjectDTO.getId(), docPath, type);
													OUT.debug("{} for subject upload file and update document status {}", subjectDTO.getName(), uploadStatus);
												}
												else
												{
													OUT.error("NULLResponse-for subject update from boxview: DocumentName: {}, subject: {}", documentName, subjectId);
												}
												try
												{
													Thread.sleep(30000);
												}
												catch (Exception e)
												{

												}
											}
											else
											{
												JSONObject uploadFileToBoxView = uploadFileToBoxView(documentPath + File.separator + documentName, name, null);
												if (null != uploadFileToBoxView)
												{

													String docPath = uploadFileToBoxView.getString("id");

													insertStatus = new DocumentDAO().insertDocumentPath(subjectDTO.getId(), docPath, type);
													OUT.debug("{} for subject upload file and insert document status {}", subjectDTO.getName(), insertStatus);
												}
												else
												{
													OUT.error("NULLResponse-for subject insert from boxview: DocumentName: {}, subject: {}", documentName, subjectId);
												}
												try
												{
													Thread.sleep(30000);
												}
												catch (Exception e)
												{

												}
											}
										}
									}
									if (subjectId == 0)
									{
										OUT.error("name {} of type {} not found in the database", name, type);
									}
								}
								else
								{
									OUT.error("name {} of type {} not found in the database", name, type);
								}
							}
						}
						else
						{
							OUT.error("file {} does not exist for name {} of type {}", documentFile, name, type);
						}
					}
				}
				catch (Exception e)
				{
					OUT.error("ExceptionUpload: DocumentName: {}, occupationId: {}, industryId: {}, subjectId: {}", documentName, occupationId, industryId,
							subjectId);
					OUT.error("Exception", e);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (wb != null)
				{
					wb.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		OUT.debug("Completed uploading documents to boxview");
	}

	private static List<OccupationDTO> getOccupationList()
	{
		List<OccupationDTO> list = null;
		try
		{
			list = new OccupationDAO().getOccupationNameAndId();
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return list;
	}

	private static List<IndustryDTO> getIndustryList()
	{
		List<IndustryDTO> list = null;
		try
		{
			list = new DocumentDAO().getIndustryList();
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return list;
	}

	private static List<SubjectDTO> getSubjectList()
	{
		List<SubjectDTO> list = null;
		try
		{
			list = new DocumentDAO().getSubjectList();
		}
		catch (Exception e)
		{
			OUT.error("Exception", e);
		}
		return list;
	}

	public static void main(String[] args)
	{
		if (args == null || args.length < 3)
		{
			OUT.error("USAGES : DocumentUpload <Excel file> <Sheet name> <Document Folder Path> ");
		}
		else
		{
			DocumentUpload.getInstance().parseExcel(args[0], args[1], args[2]);
		}
		// String excelFilePath = "D://LSData//Document_Upload.xlsx";
		// String sheetName = "document";
		// String imageFolder = "D://LSData//documents";
		// DocumentUpload.getInstance().parseExcel(excelFilePath, sheetName, imageFolder);
	}
}
