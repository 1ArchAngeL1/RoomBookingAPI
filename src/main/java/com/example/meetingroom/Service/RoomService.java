package com.example.meetingroom.Service;

import com.example.meetingroom.DTO.CasualStringDto;
import com.example.meetingroom.DTO.ChangeNumAllowedDto;
import com.example.meetingroom.DTO.ChangeRoomCreatorDto;
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
        String room_creator = roomInfo.getRoom_creator();
        roomRepository.save(new Room(num_allowed,room_creator));
    }


    public void changeNumAllowed(ChangeNumAllowedDto roomInfo, String username){
        Long room_id = roomInfo.getRoom_id();
        if(roomRepository.existsById(room_id)){
            Room room = roomRepository.getById(room_id);
            room.setPeople_allowed(roomInfo.getNum_allowed());
            roomRepository.save(room);
        }
    }

    public void giveHosting(ChangeRoomCreatorDto roomInfo, String username){
        Long room_id = roomInfo.getRoom_id();
        if(roomRepository.existsById(room_id)){
            Room room = roomRepository.getById(room_id);
            if(room.getRoom_creator().equals(username)){
                room.setRoom_creator(roomInfo.getRoom_creator());
                roomRepository.save(room);
            }
        }
    }

    public void deleteRoom(Long room_id,String username){
        if(roomRepository.existsById(room_id)){
            Room room = roomRepository.getById(room_id);
            if(room.getRoom_creator().equals(username))roomRepository.deleteById(room_id);
        }
    }

    public RoomDto getRoom(Long id,String username){
        if(roomRepository.existsById(id)){
            Room room = roomRepository.getById(id);
            if(room.getRoom_creator().equals(username)){
                RoomDto forReturn = new RoomDto(room.getPeople_allowed(),room.getRoom_creator());
                forReturn.setRoom_id(room.getRoom_id());
                return forReturn;
            }
        }
        return null;
    }

    public Room getRoomInside(Long id){
        if(roomRepository.existsById(id)){
            return roomRepository.getById(id);
        }
        return null;
    }

}
