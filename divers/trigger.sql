DROP TRIGGER IF EXISTS suppr_vol_tmp;
-- on passe le delimiter � $$ le temps de la requ�te
DELIMITER $$
CREATE TRIGGER suppr_vol_tmp
	BEFORE INSERT ON vol FOR EACH ROW
	BEGIN
		-- on supprime de la table vol_tmp un vol qui a exactement les m�mes crit�res que le nouveau vol ins�r� dans la table vol
		DELETE FROM vol_tmp WHERE lieudep = NEW.lieudep AND lieuarriv = NEW.lieuarriv AND dateheuredep = NEW.dateheuredep
		AND dateheurearrivee = NEW.dateheurearrivee AND tarif = NEW.tarif;
  	END$$
-- on r�tablit le point-virgule comme delimiter
DELIMITER ;
