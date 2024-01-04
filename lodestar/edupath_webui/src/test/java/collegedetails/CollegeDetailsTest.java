package collegedetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.lodestar.edupath.dataaccessobject.dao.college.CollegeDAO;
import com.lodestar.edupath.datatransferobject.dto.collegedetails.CollegeDetailsVO;

public class CollegeDetailsTest
{
	@Test
	public void getCollegeDetails()
	{
		try
		{
			Map<Object, Object> collegeDetails = new HashMap<Object, Object>();
			collegeDetails.put("collegeIds", new Integer[]
			{
				1
			});
			collegeDetails.put("streamId", 1);
			List<CollegeDetailsVO> collegeDetailsByCollegeIds = new CollegeDAO().getCollegeDetailsByCollegeIds(collegeDetails);
			System.out.println(collegeDetailsByCollegeIds);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
