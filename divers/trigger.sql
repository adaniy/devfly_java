DROP TRIGGER IF EXISTS suppr_vol_tmp;
-- on passe le delimiter à $$ le temps de la requête
DELIMITER $$
CREATE TRIGGER suppr_vol_tmp
	BEFORE INSERT ON vol FOR EACH ROW
	BEGIN
		-- on supprime de la table vol_tmp un vol qui a exactement les mêmes critères que le nouveau vol inséré dans la table vol
		DELETE FROM vol_tmp WHERE lieudep = NEW.lieudep AND lieuarriv = NEW.lieuarriv AND dateheuredep = NEW.dateheuredep
		AND dateheurearrivee = NEW.dateheurearrivee AND tarif = NEW.tarif;
  	END$$
-- on rétablit le point-virgule comme delimiter
DELIMITER ;
