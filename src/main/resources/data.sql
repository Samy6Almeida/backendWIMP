INSERT INTO user (id, name, username, password) VALUES (1, 'Admin User', 'admin', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu') ON DUPLICATE KEY UPDATE username="admin", password='$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu';

ALTER TABLE `WhereIsMyParty`.`party`
DROP FOREIGN KEY `FKtcag4fsdqkmo7owjkk1p25h41`;
ALTER TABLE `WhereIsMyParty`.`party`
ADD CONSTRAINT `FKtcag4fsdqkmo7owjkk1p25h41`
  FOREIGN KEY (`user_id`)
  REFERENCES `WhereIsMyParty`.`user` (`id`)
  ON DELETE CASCADE;

ALTER TABLE `WhereIsMyParty`.`party_x_music`
DROP FOREIGN KEY `FKfu3tiay4q141yymrqhtbte9xa`;
ALTER TABLE `WhereIsMyParty`.`party_x_music`
ADD CONSTRAINT `FKfu3tiay4q141yymrqhtbte9xa`
  FOREIGN KEY (`party_id`)
  REFERENCES `WhereIsMyParty`.`party` (`id`)
  ON DELETE CASCADE;

  ALTER TABLE `WhereIsMyParty`.`party_x_partfeatures`
DROP FOREIGN KEY `FK28dr7k2ph1ma14yf8f0chcu9l`;
ALTER TABLE `WhereIsMyParty`.`party_x_partfeatures`
ADD CONSTRAINT `FK28dr7k2ph1ma14yf8f0chcu9l`
  FOREIGN KEY (`party_id`)
  REFERENCES `WhereIsMyParty`.`party` (`id`)
  ON DELETE CASCADE;


ALTER TABLE `WhereIsMyParty`.`party_x_partykind`
DROP FOREIGN KEY `FKkfcdwqdirlv66yo1eivmas0vq`;
ALTER TABLE `WhereIsMyParty`.`party_x_partykind`
ADD CONSTRAINT `FKkfcdwqdirlv66yo1eivmas0vq`
  FOREIGN KEY (`party_id`)
  REFERENCES `WhereIsMyParty`.`party` (`id`)
  ON DELETE CASCADE;

  ALTER TABLE `WhereIsMyParty`.`denunciation`
DROP FOREIGN KEY `FKgigh0a0mbloxrcwb05mj8t0ro`,
DROP FOREIGN KEY `FKhofbrf3rkjn1oik3fnu3lwnkj`;
ALTER TABLE `WhereIsMyParty`.`denunciation`
ADD CONSTRAINT `FKgigh0a0mbloxrcwb05mj8t0ro`
  FOREIGN KEY (`party_id`)
  REFERENCES `WhereIsMyParty`.`party` (`id`)
  ON DELETE CASCADE,
ADD CONSTRAINT `FKhofbrf3rkjn1oik3fnu3lwnkj`
  FOREIGN KEY (`user_id`)
  REFERENCES `WhereIsMyParty`.`user` (`id`)
  ON DELETE CASCADE;

INSERT INTO `WhereIsMyParty`.`musickind` (`id`, `music_kind`) VALUES ('1', 'ROCK') ON DUPLICATE KEY UPDATE music_kind='ROCK';
INSERT INTO `WhereIsMyParty`.`musickind` (`id`, `music_kind`) VALUES ('2', 'ELETRÔNICA')ON DUPLICATE KEY UPDATE music_kind='ELETRÔNICA';
INSERT INTO `WhereIsMyParty`.`musickind` (`id`, `music_kind`) VALUES ('3', 'FORRÓ')ON DUPLICATE KEY UPDATE music_kind='FORRÓ';
INSERT INTO `WhereIsMyParty`.`musickind` (`id`, `music_kind`) VALUES ('4', 'COUNTRY')ON DUPLICATE KEY UPDATE music_kind='COUNTRY';
INSERT INTO `WhereIsMyParty`.`musickind` (`id`, `music_kind`) VALUES ('5', 'BLUES')ON DUPLICATE KEY UPDATE music_kind='BLUES';
INSERT INTO `WhereIsMyParty`.`musickind` (`id`, `music_kind`) VALUES ('6', 'HIP HOP')ON DUPLICATE KEY UPDATE music_kind='HIP HOP';

INSERT INTO `WhereIsMyParty`.`partyfeatures` (`id`, `partyfeatures`) VALUES ('1', 'OPEN BAR') ON DUPLICATE KEY UPDATE partyfeatures='OPEN BAR';
INSERT INTO `WhereIsMyParty`.`partyfeatures` (`id`, `partyfeatures`) VALUES ('2', 'À FANTASIA') ON DUPLICATE KEY UPDATE partyfeatures='À FANTASIA';
INSERT INTO `WhereIsMyParty`.`partyfeatures` (`id`, `partyfeatures`) VALUES ('3', 'RELIGIOSA') ON DUPLICATE KEY UPDATE partyfeatures='RELIGIOSA';
INSERT INTO `WhereIsMyParty`.`partyfeatures` (`id`, `partyfeatures`) VALUES ('4', 'BENEFICENTE') ON DUPLICATE KEY UPDATE partyfeatures='BENEFICENTE';


INSERT INTO `WhereIsMyParty`.`partykind` (`id`, `kind`) VALUES ('1', 'Festa Privada') ON DUPLICATE KEY UPDATE kind='Festa Privada';
INSERT INTO `WhereIsMyParty`.`partykind` (`id`, `kind`) VALUES ('2', 'Festa Publica') ON DUPLICATE KEY UPDATE kind='Festa Publica';
INSERT INTO `WhereIsMyParty`.`partykind` (`id`, `kind`) VALUES ('3', 'LGBT') ON DUPLICATE KEY UPDATE kind='LGBT';

