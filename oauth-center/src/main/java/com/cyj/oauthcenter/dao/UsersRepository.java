package com.cyj.oauthcenter.dao;


import com.cyj.oauthcenter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 
 * @Description: 用户Dao接口
 * @ClassName: UsersRepository.java
 * @author Chenyongjia
 * @Date 2018年11月12日 下午22:01
 * @Email 867647213@qq.com
 */
public interface UsersRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

	/**
	 * 根据姓名查询
	 * @param userName
	 * @author Chenyongjia
	 * @return
	 */
	User findByUserName(String userName);

}
