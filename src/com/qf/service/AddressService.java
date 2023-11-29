package com.qf.service;

import com.qf.domain.Address;

import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/28 16:08
 * description:
 */
public interface AddressService {
    List<Address> find(Integer uid);

    void add(Address address);

    void remove(Integer uid, int aid);

    void updateLevel(Integer uid, int aid);

    void update(Address address);
}
