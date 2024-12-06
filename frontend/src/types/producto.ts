// src/types/producto.ts
export interface Producto {
    idComida: string;
    nombreComida: string;
    tipoComida: string;
    descripcion: string;
    precio: number;
    imagenUrl: string;
    local: {
      idLocal: number;
      ubicacion: string;
    };
  }
  