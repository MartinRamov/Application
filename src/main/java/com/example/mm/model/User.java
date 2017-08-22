package com.example.mm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.*;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Win8.1 on 28.06.2017.
 */
@Entity
@Table(name = "users")
@Indexed
@AnalyzerDef(name = "userAnalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {@TokenFilterDef(factory = LowerCaseFilterFactory.class)})
public class User extends BaseEntity {

    @Field(index = Index.YES, store = Store.NO, analyze = Analyze.YES)
    @Analyzer(definition = "userAnalyzer")
    @Boost(2f)
    @Column
    public String firstName;

    @Field(index = Index.YES, store = Store.NO, analyze = Analyze.YES)
    @Analyzer(definition = "userAnalyzer")
    @Boost(2f)
    @Column
    public String lastName;

    @Column(unique = true)
    public String email;

    @Column
    public String password;

    @Column
    public Boolean isPremiumUser;

    //@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE})
    @JoinTable(name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    public Set<User> friends = new TreeSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE})
    @JoinTable(name = "user_friends",
            joinColumns = @JoinColumn(name = "friend_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    public Set<User> friendOf = new TreeSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_meetings",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "meeting_id"))
    public Set<Meeting> meetings = new TreeSet<>();


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_chats",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id"))
    public Set<Chat> chats = new TreeSet<>();


    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = {CascadeType.ALL, CascadeType.REMOVE})
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public Set<Activity> activities = new TreeSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = {CascadeType.ALL, CascadeType.REMOVE})
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public Set<ChatItem> chatItems = new TreeSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "receiver", cascade = {CascadeType.ALL, CascadeType.REMOVE})
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public Set<Notification> notifications = new TreeSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sender", cascade = CascadeType.REMOVE)
    public Set<FriendRequest> sentRequests = new TreeSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "receiver", cascade = CascadeType.REMOVE)
    public Set<FriendRequest> receivedRequests = new TreeSet<>();


    @Override
    public String toString() {
        return String.format("%d. %s %s %s", id, firstName, lastName, email);
    }
}
