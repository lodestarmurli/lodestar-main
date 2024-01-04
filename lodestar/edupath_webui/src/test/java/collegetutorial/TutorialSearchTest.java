package collegetutorial;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.lodestar.edupath.dataaccessobject.dao.tutorial.TutorialDAO;
import com.lodestar.edupath.datatransferobject.dto.tutorialsearch.TutorialSearchVO;
import com.lodestar.edupath.tutorialsearch.bean.TutorialSearchFilterVO;

public class TutorialSearchTest
{

	@Test
	public void getTutorialDetails()
	{
		try
		{
			TutorialSearchFilterVO searchFilterVO = new TutorialSearchFilterVO();
			searchFilterVO.setGetTotalCount(false);
			searchFilterVO.setExamId(1);
			List<Integer> cityIds = new ArrayList<Integer>();
			cityIds.add(1);
			searchFilterVO.setCityIds(cityIds);
			searchFilterVO.setStudentId(1);
			searchFilterVO.setLocality("Banashankari");
			ObjectMapper mapper = new ObjectMapper();
			Map<Object, Object> convertValue = mapper.convertValue(searchFilterVO, Map.class);
			int totalSize = new TutorialDAO().getTutorialsDetailsTotalSize(convertValue);
			System.out.println(totalSize);
			List<TutorialSearchVO> tutorialsDetails = new TutorialDAO().getTutorialsDetails(convertValue, 0, 1);
			System.out.println(tutorialsDetails);
			assertTrue("Tutorial details not found", tutorialsDetails.size() > 0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
