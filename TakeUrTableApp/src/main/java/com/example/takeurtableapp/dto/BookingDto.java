package com.example.takeurtableapp.dto;

import com.example.takeurtableapp.entity.Restaurant;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private Long id;
    @NotEmpty
    private Long restaurantId;
    @NotEmpty
    private String userName;
    @NotEmpty
    private String userPhone;
    @NotEmpty
    private String date;
    @NotEmpty
    private String time;
    @NotEmpty
    private int numberOfPeople;
    @NotEmpty
    private String eventType;
    private String commentUser;
    private String commentAdmin;
    @NotEmpty
    private String status;

    public BookingDto(Long restaurantId, String userName, String userPhone){
        this.restaurantId = restaurantId;
        this.userName = userName;
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "BookingDto{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", numberOfPeople='" + numberOfPeople + '\'' +
                ", eventType='" + eventType + '\'' +
                ", commentUser='" + commentUser + '\'' +
                ", commentAdmin='" + commentAdmin + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
