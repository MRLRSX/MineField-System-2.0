package br.com.mfs.model;

import br.com.mfs.model.enums.CampoEvento;

public interface CampoObservador {
  
	public void eventOcorreu(Campo campo, CampoEvento campoEvento);
}
