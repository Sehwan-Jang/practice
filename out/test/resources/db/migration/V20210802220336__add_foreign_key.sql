alter table player add team_id bigint  not null;
alter table player add constraint player_team foreign key(team_id) references team(id);