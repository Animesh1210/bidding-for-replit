package com.enam.bid.seller.entity.ondc;

import com.enam.bid.seller.entity.base.AuditableBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "locations", schema = "ondc_schema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location extends AuditableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "gps") // Mapped as String, actual type is POINT
    private String gps; // Consider using a spatial library type if needed (e.g., org.locationtech.jts.geom.Point)

    @Column(name = "address", columnDefinition = "text")
    private String address;

    @Column(name = "city_code", length = 50)
    private String cityCode;

    @Column(name = "city_name_id") // Assuming this might link to another table not shown, mapped as Integer
    private Integer cityNameId;

    @Column(name = "state_code", length = 50)
    private String stateCode;

    @Column(name = "state_name", length = 100)
    private String stateName;

    @Column(name = "country_code", length = 10)
    private String countryCode;

    @Column(name = "area_code")
    private Integer areaCode;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ProviderLocation> providerLocations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<FulfillmentStop> fulfillmentStops = new LinkedHashSet<>();
}
