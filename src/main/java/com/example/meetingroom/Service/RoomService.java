package com.example.meetingroom.Service;

import com.example.meetingroom.DTO.*;
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


    public Response addRoom(RoomDto roomInfo){
        int num_allowed = roomInfo.getNum_allowed();
        String room_creator = roomInfo.getRoom_creator();
        Room newRoom = new Room(num_allowed,room_creator);
        roomRepository.save(newRoom);
        roomInfo.setRoom_id(newRoom.getRoom_id());
        return new Response(roomInfo);
    }


    public Response changeNumAllowed(ChangeNumAllowedDto roomInfo, String username){
        Long room_id = roomInfo.getRoom_id();
        if(roomRepository.existsById(room_id)){
            Room room = roomRepository.getById(room_id);
            if(!room.getRoom_creator().equals(username))return new Response(new ErrorMessege("access denied"));
            room.setPeople_allowed(roomInfo.getNum_allowed());
            roomRepository.save(room);
            RoomDto dto = new RoomDto(room.getPeople_allowed());
            dto.setRoom_creator(room.getRoom_creator());
            dto.setRoom_id(room.getRoom_id());
            return new Response(dto);
        }
        return new Response(new ErrorMessege("you dont have access to this room"));
    }

    public Response giveHosting(ChangeRoomCreatorDto roomInfo, String username){
        Long room_id = roomInfo.getRoom_id();
        if(roomRepository.existsById(room_id)){
            Room room = roomRepository.getById(room_id);
            if(room.getRoom_creator().equals(username)){
                room.setRoom_creator(roomInfo.getRoom_creator());
                roomRepository.save(room);
                RoomDto dto = new RoomDto(room.getPeople_allowed());
                dto.setRoom_creator(room.getRoom_creator());
                dto.setRoom_id(room.getRoom_id());
                return new Response(dto);
            }else {
               return new Response(new ErrorMessege("access denied"));
            }
        }
        return new Response(new ErrorMessege("you dont have access to this room"));
    }

    public Response deleteRoom(Long room_id,String username){
        if(roomRepository.existsById(room_id)){
            Room room = roomRepository.getById(room_id);
            if(!room.getRoom_creator().equals(username))return new Response(new ErrorMessege("access denied"));
            roomRepository.deleteById(room_id);
            RoomDto dto = new RoomDto(room.getPeople_allowed());
            dto.setRoom_creator(room.getRoom_creator());
            dto.setRoom_id(room.getRoom_id());
            return new Response(dto);
        }
        return new Response(new ErrorMessege("you dont have access to this room"));
    }

    public Response getRoom(Long id,String username){
        if(roomRepository.existsById(id)){
            Room room = roomRepository.getById(id);
            if(room.getRoom_creator().equals(username)){
                RoomDto dto = new RoomDto(room.getPeople_allowed());
                dto.setRoom_creator(room.getRoom_creator());
                dto.setRoom_id(room.getRoom_id());
                return new Response(dto);
            }else{
                return new Response(new ErrorMessege("access denied"));
            }
        }
        return new Response(new ErrorMessege("bad credintials"));
    }

    public Room getRoomInside(Long id){
        if(roomRepository.existsById(id)){
            return roomRepository.getById(id);
        }
        return null;
    }

}
