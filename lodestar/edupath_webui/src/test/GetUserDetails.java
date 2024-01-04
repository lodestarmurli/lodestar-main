import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lodestar.edupath.dataaccessobject.MyBatisManager;
import com.lodestar.edupath.datatransferobject.dto.role.UserDetailDTO;
import com.lodestar.edupath.datatransferobject.dto.student.StudentDetailsDTO;
import com.lodestar.edupath.datatransferobject.util.AESCipher;

class GetUserDetails
{
	printUserDetails()
	{
		Map<String, Object> parameterObject = new HashMap<>();
		parameterObject.put("schoolId", 2);
		List<StudentDetailsDTO> resultList2 = MyBatisManager.getInstance().getResultList("StudentDetails.getAllStudentDetails", parameterObject);
		List<UserDetailDTO> resultList = MyBatisManager.getInstance().getResultList("UserDetail.tempQuery", parameterObject);
		for (UserDetailDTO userDetailDTO : resultList)
		{
			for (StudentDetailsDTO student : resultList2)
			{
				if(student.getUserId() == userDetailDTO.getId())
				{
					System.out.println(student.getName() + "\t" + userDetailDTO.getLoginId() + "/"
							+ new String(AESCipher.decrypt(userDetailDTO.getPassword())));
					
					break;
				}
			}
		}
	}
}