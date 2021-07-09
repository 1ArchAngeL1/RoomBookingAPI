package com.example.meetingroom.Service;

import com.example.meetingroom.DTO.EditRoomDto;
import com.example.meetingroom.DTO.RoomDto;
import com.example.meetingroom.Entity.Room;
import com.example.meetingroom.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public void addRoom(RoomDto roomInfo){
        int num_allowed = roomInfo.getNum_allowed();
        roomRepository.save(new Room(num_allowed));
    }

    public void editRoom(EditRoomDto roomInfo){
        Long room_id = roomInfo.getRoom_id();
        int num_allowed = roomInfo.getNum_allowed();
        if(roomRepository.existsById(room_id)){
            Room room = roomRepository.getById(room_id);
            room.setPeople_allowed(num_allowed);
            roomRepository.save(room);
        }
    }


    public void deleteRoom(Long room_id){
        if(roomRepository.existsById(room_id)){
            roomRepository.deleteById(room_id);
        }
    }

    public Room getRoom(Long room_id){
        if(roomRepository.existsById(room_id))return roomRepository.getById(room_id);
        return null;
    }

}
