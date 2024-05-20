package com.example.takeurtableapp.service.iml;
import com.example.takeurtableapp.dto.BookingDto;
import com.example.takeurtableapp.entity.Booking;
import com.example.takeurtableapp.entity.Restaurant;
import com.example.takeurtableapp.repository.BookingRepository;
import com.example.takeurtableapp.repository.RestaurantRepository;
import com.example.takeurtableapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    //private PasswordEncoder passwordEncoder;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public List<Booking> getBookingsByRestaurantId(Long restaurantId) {
        return bookingRepository.findAll().stream()
                .filter(booking -> booking.getRestaurant().getId().equals(restaurantId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> getUnconfirmedBookingsByRestaurantId(Long restaurantId) {
        return bookingRepository.findAll().stream()
                .filter(booking -> booking.getRestaurant().getId().equals(restaurantId) && "Не підтверджено".equals(booking.getStatus()))
                .collect(Collectors.toList());

    }
    @Override
    public List<Booking> getConfirmedBookingsByRestaurantId(Long restaurantId) {
        return bookingRepository.findAll().stream()
                .filter(booking -> booking.getRestaurant().getId().equals(restaurantId) && "Підтверджено".equals(booking.getStatus()))
                .collect(Collectors.toList());

    }
    @Override
    public List<Booking> getArchivedBookingsByRestaurantId(Long restaurantId) {
        return bookingRepository.findAll().stream()
                .filter(booking -> booking.getRestaurant().getId().equals(restaurantId) && "Архівовано".equals(booking.getStatus()))
                .collect(Collectors.toList());

    }

    @Override
    public void saveBooking(BookingDto bookingDto){
        Booking booking = new Booking();
        booking.setUserName(bookingDto.getUserName());
        booking.setUserPhone(bookingDto.getUserPhone());
        booking.setNumberOfPeople(bookingDto.getNumberOfPeople());
        booking.setEventType(bookingDto.getEventType());
        booking.setCommentUser(bookingDto.getCommentUser());
        booking.setCommentAdmin(bookingDto.getCommentAdmin());
        booking.setStatus(bookingDto.getStatus());
        LocalDateTime dateTime = parseDateTime(bookingDto.getDate(), bookingDto.getTime());
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        booking.setDate(timestamp);
        Restaurant restaurant = restaurantRepository.findById(bookingDto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Ресторан з ідентифікатором " + bookingDto.getRestaurantId() + " не знайдено"));
        booking.setRestaurant(restaurant);
        bookingRepository.save(booking);
    }
    private static LocalDateTime parseDateTime(String dateString, String timeString) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalDateTime date = LocalDate.parse(dateString, dateFormatter).atStartOfDay();
        LocalDateTime time = LocalTime.parse(timeString, timeFormatter).atDate(date.toLocalDate());

        LocalDateTime dateTime = date.withHour(time.getHour()).withMinute(time.getMinute());

        return dateTime;
    }
}
