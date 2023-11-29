package com.qf.service.impl;

import com.qf.dao.AddressDao;
import com.qf.dao.impl.AddressDaoImpl;
import com.qf.domain.Address;
import com.qf.service.AddressService;

import java.util.List;

/**
 * projectName:myxiaomi
 * author:HuShanTao
 * time:2023/11/28 16:08
 * description:
 */
public class AddressServiceImpl implements AddressService {
    private AddressDao addressDao = new AddressDaoImpl();

    @Override
    public List<Address> find(Integer uid) {
        return addressDao.select(uid);
    }

    @Override
    public void add(Address address) {
        addressDao.insert(address);
    }

    @Override
    public void remove(Integer uid, int aid) {
        addressDao.delete(uid,aid);
    }

    @Override
    public void updateLevel(Integer uid, int aid) {
        addressDao.updateLevel(uid,aid);
    }

    @Override
    public void update(Address address) {
        addressDao.update(address);
    }
}
