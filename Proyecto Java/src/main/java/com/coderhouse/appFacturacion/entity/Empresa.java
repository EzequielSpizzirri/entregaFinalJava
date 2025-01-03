package com.coderhouse.appFacturacion.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "EMPRESA")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
	
	@Column(name = "ID_EMPRESA")
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "La razón social de la empresa es obligatoria")
	@Column(name = "RAZON_SOCIAL")
	private String razonSocial;
	
	@NotBlank(message = "El rubo de la empresa es obligatorio")
	@Column(name = "RUBRO")
	private String rubro;

	@NotBlank(message = "El cuit de la empresa es obligatorio")
	@Column(name = "CUIT")
	private String cuit;
	
	@JsonIgnore
	@OneToMany(mappedBy="empresa", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Factura> factura;
	
	
	
	
}
