
ALTER TABLE WishList ADD COLUMN `occIndustryId` INTEGER UNSIGNED AFTER isSystemRecommended;
ALTER TABLE ShortList ADD COLUMN `occIndustryId` INTEGER UNSIGNED AFTER isSystemRecommended;

CREATE TABLE OccupationIndustryMapping (
   id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
   occupationId INTEGER UNSIGNED NOT NULL,
   industryId INTEGER UNSIGNED NOT NULL,
   pathwayId INTEGER UNSIGNED NOT NULL,
   isPrimary TINYINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO OccupationIndustryMapping (occupationId, industryId, pathwayId, isPrimary)
(SELECT id, industryId, pathwayId, true FROM Occupation );

Update WishList w set OccIndustryId =
(SELECT industryId From OccupationIndustryMapping oim
 WHERE oim.occupationId = w.occupationId and oim.isPrimary);

Update ShortList w set OccIndustryId =
(SELECT industryId From OccupationIndustryMapping oim
 WHERE oim.occupationId = w.occupationId and oim.isPrimary); 
 
ALTER TABLE Occupation DROP COLUMN industryId,
 DROP COLUMN pathWayId;

ALTER TABLE Tags DROP COLUMN industryId,
DROP COLUMN pathWayId;
 
ALTER TABLE WishList DROP COLUMN industryId; 