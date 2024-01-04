package collegesearch;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.lodestar.edupath.dataaccessobject.dao.college.CollegeDAO;
import com.lodestar.edupath.datatransferobject.dto.collegesearch.CollegeSearchVO;
import com.lodestar.edupath.finaliseelectives.service.bean.CollegeSearchFilterVO;

public class CollegeSearchTest
{
	@Test
	public void getCollegeResult()
	{
		try
		{
			CollegeSearchFilterVO collegeSearchFilter = new CollegeSearchFilterVO();
			// Activity
			// List<Integer> activteIds = new ArrayList<Integer>();
			// activteIds.add(1);
			// collegeSearchFilter.setActivteIds(activteIds);
			collegeSearchFilter.setPageNo(1);
			collegeSearchFilter.setStreamId(1);
			List<Integer> combinationIds = new ArrayList<Integer>();
			combinationIds.add(1);
			combinationIds.add(2);
			collegeSearchFilter.setCombinationIds(combinationIds);

			collegeSearchFilter.setFeeFrom(0);
			collegeSearchFilter.setFeeTo(30000);
			collegeSearchFilter.setGetTotalCount(false);

			List<Integer> affiliationIds = new ArrayList<Integer>();
			affiliationIds.add(1);
			collegeSearchFilter.setAffiliationIds(affiliationIds);
			collegeSearchFilter.setAdmCutOffFrom(0);
			collegeSearchFilter.setAdmCutOffTo(100);

			collegeSearchFilter.setYoeFrom(0);
			collegeSearchFilter.setYoeTo(100);

			collegeSearchFilter.setStudentPassFrom(0);
			collegeSearchFilter.setStudentPassTo(100);

			// List<Integer> infrastructureIds = new ArrayList<Integer>();
			// infrastructureIds.add(1);
			// infrastructureIds.add(2);
			// collegeSearchFilter.setInfrastructureIds(infrastructureIds);

			List<Integer> reservationIds = new ArrayList<Integer>();
			reservationIds.add(1);
			collegeSearchFilter.setReservationIds(reservationIds);

			collegeSearchFilter.setCoachingFacility(true);
			collegeSearchFilter.setStudentId(1);
			collegeSearchFilter.setLocality("nagarbhavi");

			// @SuppressWarnings("unchecked")
			// Map<Object, Object> collegeSearchFilterMap = new BeanMap(collegeSearchFilter);
			ObjectMapper m = new ObjectMapper();
			Map<Object, Object> collegeSearchFilterMap = m.convertValue(collegeSearchFilter, Map.class);
			List<CollegeSearchVO> collegeDetails = new CollegeDAO().getCollegeDetails(collegeSearchFilterMap, 1, 9);
			assertTrue("College result not found for filter", (collegeDetails.size() > 0));
			System.out.println(collegeDetails);

			if (collegeSearchFilter.isGetTotalCount())
			{
				for (CollegeSearchVO collegeSearchVO : collegeDetails)
				{
					assertTrue("Only id should come", collegeSearchVO.getAddress() == null);
				}
			}
			else
			{
				for (CollegeSearchVO collegeSearchVO : collegeDetails)
				{
					assertTrue("Only id should come", collegeSearchVO.getAddress() != null);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
