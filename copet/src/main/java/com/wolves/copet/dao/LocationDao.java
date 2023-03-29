package com.wolves.copet.dao;

import com.wolves.copet.dto.Location;

import java.util.List;

public interface LocationDao {
    public Location getLocation(Integer id);
    public List<Location> getAllLocations();
    public Location updateLocation(Integer id,String field,String updatedInfo);
}
