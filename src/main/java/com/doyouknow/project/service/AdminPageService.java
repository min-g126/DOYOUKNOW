package com.doyouknow.project.service;

import com.doyouknow.project.dto.MemberDTO;
import com.doyouknow.project.entity.Member;
import com.doyouknow.project.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminPageService {

    private ModelMapper modelMapper;

    @Autowired
    private MemberRepository memberRepository;

    public AdminPageService(ModelMapper modelMapper, MemberRepository memberRepository){
        this.modelMapper=modelMapper;
        this.memberRepository=memberRepository;
    }

    @Transactional
    public List<Member> findMemberByStatus(int status){
        return  memberRepository.findMemberByStatus(status);
    }

    @Transactional
    public List<Member> findMemberByType(int type){
        return  memberRepository.findMemberByType(type);
    }

    @Transactional
    public void updateMemberStatus(int status, int seq){
        memberRepository.updateMemberStatus(status, seq);
    }

    @Transactional
    public void deleteMember(int seq){
        memberRepository.deleteMemberBySeq(seq);
    }
}
