'use client';
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Alert, Button } from 'reactstrap';

interface Mesa {
  idMesa: number;
  nroMesa: number;
  estado: string;
}

const LiberarMesaPage: React.FC = () => {
  const [mesas, setMesas] = useState<Mesa[]>([]);
  const [alertMessage, setAlertMessage] = useState<string | null>(null);
  const [alertColor, setAlertColor] = useState<'success' | 'danger'>('success');

  useEffect(() => {
    // Obtener todas las mesas ocupadas desde la API
    const fetchMesas = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/mesas/ver');
        const mesasOcupadas = response.data.filter((mesa: Mesa) => mesa.estado === 'Ocupada');
        setMesas(mesasOcupadas);
      } catch (error) {
        console.error('Error al cargar las mesas:', error);
      }
    };

    fetchMesas();
  }, []);

  const liberarMesa = async (idMesa: number) => {
    try {
      // Cambiar el estado de la mesa a "disponible"
      await axios.put(`http://localhost:8080/api/mesas/editar/${idMesa}`, { estado: 'disponible' });

      // Actualizar el estado del frontend para remover la mesa de la lista
      setMesas(mesas.filter((mesa) => mesa.idMesa !== idMesa));

      // Mostrar alerta de éxito
      setAlertMessage(`Mesa N°${idMesa} liberada con éxito.`);
      setAlertColor('success');
    } catch (error) {
      console.error('Error al liberar la mesa:', error);
      setAlertMessage('Error al liberar la mesa.');
      setAlertColor('danger');
    }
  };

  return (
    <div className="p-8 bg-white min-h-screen">
      <h2 className="text-3xl font-bold text-center mb-8">Mesas Ocupadas</h2>

      {alertMessage && (
        <Alert color={alertColor} toggle={() => setAlertMessage(null)} className="mb-4">
          {alertMessage}
        </Alert>
      )}

      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-3 gap-6">
        {mesas.map((mesa) => (
          <div key={mesa.idMesa} className="text-center">
            <h3 className="text-xl font-bold mb-2">Mesa N°{mesa.nroMesa}</h3>
            <img
              src="/assets/mesa-cuatro.jpg" // Ruta a la imagen de la mesa
              alt={`Mesa N°${mesa.nroMesa}`}
              className="w-full h-48 object-cover mb-4 rounded-lg"
            />
            <button
              onClick={() => liberarMesa(mesa.idMesa)}
              className="bg-green-500 text-white py-2 px-4 rounded-lg hover:bg-green-600 transition-all"
            >
              Liberar Mesa
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default LiberarMesaPage;
