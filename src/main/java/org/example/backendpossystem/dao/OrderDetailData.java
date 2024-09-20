package org.example.backendpossystem.dao;

import org.example.backendpossystem.dto.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailData {
    public boolean saveOrderDetail(Connection connection, OrderDetail orderDetailDto) throws SQLException;

    List<OrderDetail> getAll(Connection connection, String orderId) throws SQLException;
}
