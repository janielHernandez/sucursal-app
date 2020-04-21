package com.example.sucursalapp.dtos;


import com.example.sucursalapp.entities.PointVO;

public class BranchOfficeDto extends PointVO {

    private Long id;
    private AddressDto address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "BranchOfficeDto{" +
                "id=" + id +
                ", address=" + address +
                ", longitude=" + getLongitude() +
                ", latitude=" + getLatitude() +
                '}';
    }
}
