package org.example.backendpossystem.dao.impl;

import org.example.backendpossystem.dao.OrderDetailData;
import org.example.backendpossystem.dto.OrderDetail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDataProcess implements OrderDetailData {
    static String SAVE_ORDER_DETAIL = "INSERT INTO orderDetails (orderId,customerId,itemId,qty,unitPrice) VALUES (?,?,?,?,?)";
    static String DELETE_ORDER_DETAIL = "DELETE FROM orderDetails WHERE orderId=?";
    static String GET_ORDER_DETAIL = "SELECT * FROM orderDetails WHERE orderId=?";
    @Override
    public boolean saveOrderDetail(Connection connection, OrderDetail orderDetailDto) throws SQLException {
        try(var pstm = connection.prepareStatement(SAVE_ORDER_DETAIL)){
            pstm.setString(1, orderDetailDto.getOrderId());
            pstm.setString(2, orderDetailDto.getCustomerId());
            pstm.setString(3, orderDetailDto.getItemId());
            pstm.setInt(4, orderDetailDto.getQty());
            pstm.setDouble(5, orderDetailDto.getUnitPrice());
            return pstm.executeUpdate()!=0;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public List<OrderDetail> getAll(Connection connection, String orderId) throws SQLException {
        try(var pstm = connection.prepareStatement(GET_ORDER_DETAIL)){
            pstm.setString(1,orderId);
            ArrayList<OrderDetail> orderItemlist = new ArrayList<>();
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                OrderDetail orderDetailDto = new OrderDetail();
                orderDetailDto.setOrderId(rs.getString("orderId"));
                orderDetailDto.setCustomerId(rs.getString("customerId"));
                orderDetailDto.setItemId(rs.getString("itemId"));
                orderDetailDto.setQty(rs.getInt("qty"));
                orderDetailDto.setUnitPrice(rs.getDouble("unitPrice"));
                orderItemlist.add(orderDetailDto);
            }
            return orderItemlist;
        } catch (SQLException e) {
            throw e;
        }
    }
}
