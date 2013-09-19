DROP TRIGGER IF EXISTS suppr_vol_tmp//
CREATE TRIGGER suppr_vol_tmp BEFORE INSERT ON vol
  FOR EACH ROW
  BEGIN
	-- on supprime de la table vol_tmp un vol qui a exactement les mêmes critères que le nouveau vol inséré dans la table vol
	DELETE FROM vol_tmp WHERE lieudep = NEW.lieudep AND lieuarriv = NEW.lieuarriv AND dateheuredep = NEW.dateheuredep
	AND dateheurearrivee = NEW.dateheurearrivee AND tarif = NEW.tarif AND pilote = NEW.pilote AND copilote = NEW.copilote
	AND hotesse_steward1 = NEW.hotesse_steward1 AND hotesse_steward2 = NEW.hotesse_steward2 AND hotesse_steward3 = NEW.hotesse_steward3;
END
//