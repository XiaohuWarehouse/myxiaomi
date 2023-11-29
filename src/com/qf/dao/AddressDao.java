package com.qf.dao;

import com.qf.domain.Address;

import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/28 19:08
 * description:
 */
public interface AddressDao {
    List<Address> select(Integer uid);

    void insert(Address address);

    void delete(Integer uid, int aid);

    void updateLevel(Integer uid, int aid);

    void update(Address address);
}
