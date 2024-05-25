package com.reins.bookstore.models;

import lombok.Data;

import java.util.List;

@Data
public class OrderInfo {
    String address;
    String receiver;
    String tel;
    List<Long> itemIds;
}
