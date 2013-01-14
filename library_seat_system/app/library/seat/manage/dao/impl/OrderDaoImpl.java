package library.seat.manage.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import play.db.DB;

import library.seat.manage.dao.BaseDao;
import library.seat.manage.dao.IOrderDao;
import library.seat.manage.dto.OrdersInfo;
import library.seat.manage.dto.PageInfo;
import library.seat.manage.exception.DataAccessException;
import library.seat.manage.util.FieldValueCriteria;

/**
 * 
 * @author mahs
 * @version 1.0
 * @created 1-1-2013 22:50:00
 */
public class OrderDaoImpl extends BaseDao implements IOrderDao {
	
	private static final String TABLE = "ORDER"; 

	@Override
	public int add(OrdersInfo order) throws DataAccessException {
		return 0;
	}

	@Override
	public void delete(int orderId) throws DataAccessException {
		
	}

	@Override
	public int getCount(List<FieldValueCriteria> criteria)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrdersInfo> findByCriteria(List<FieldValueCriteria> criteria)
			throws DataAccessException {
		StringBuilder queryRecs = new StringBuilder("select * from " + TABLE + " where 1 = 1");
		appendCriterias(criteria, queryRecs);
		
		Connection conn = DB.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<OrdersInfo> userList = new ArrayList<OrdersInfo>();
		try {
			statement = conn.prepareStatement(queryRecs.toString());
		    for(int i=0; i<criteria.size(); i++) {
		    	statement.setObject(i+1, criteria.get(i).getFieldValue());
		    }
		    rs = statement.executeQuery();
		    while(rs.next()) {
		    	OrdersInfo order = new OrdersInfo();
		    	order.setId(rs.getInt("ID"));
		    	order.setDeskId(rs.getInt("DESK_ID"));
		    	order.setUserId(rs.getInt("USER_ID"));
		    	order.setDeskNum(rs.getInt("DESK_NUM"));
		    	order.setDeskNum(rs.getInt("DESK_NUM"));
		    	order.setReserveType(rs.getString("RESERVE_TYPE"));
		    	order.setReserveBeginTime(rs.getTimestamp("RESERVE_BEGIN_TIME"));
		    	order.setReserveEndTime(rs.getTimestamp("RESERVE_END_TIME"));
		    }
		    return userList;
		 } catch (SQLException e) {
			 throw new DataAccessException("error when search record", e);
		 } finally {
			 if (statement != null) {
				 try {
					 statement.close();
			     } catch (SQLException e) {
			    	 e.printStackTrace();
			     }
			}
		 }
	}

	@Override
	public PageInfo<OrdersInfo> findByCriteria(
			List<FieldValueCriteria> criteria, PageInfo pageInfo)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
