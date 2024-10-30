import React, { useState } from 'react';

interface ModalEliminarLocalProps {
  onClose: () => void;
}

const ModalEliminarLocal: React.FC<ModalEliminarLocalProps> = ({ onClose }) => {
  const [idLocal, setIdLocal] = useState<number | null>(null);

  const handleEliminarLocal = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!idLocal) {
      console.error('Debe ingresar un ID de Local');
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/api/locales/eliminar/${idLocal}`, {
        method: 'DELETE',
      });

      if (!response.ok) {
        throw new Error('Error al eliminar el local');
      }

      console.log('Local eliminado con éxito');
      onClose();
    } catch (error) {
      console.error('Error al eliminar local:', error);
    }
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
      <div className="relative bg-white rounded-lg p-8 w-96">
        <button
          onClick={onClose}
          className="absolute top-2 right-2 bg-red-500 text-white rounded-md p-2 hover:bg-red-600"
        >
          &times;
        </button>
        <h2 className="text-2xl font-bold mb-4 text-black">Eliminar Local</h2>
        <form onSubmit={handleEliminarLocal}>
          <div className="mb-4">
            <label className="block text-gray-700">ID del Local a eliminar:</label>
            <input
              type="number"
              value={idLocal || ''}
              onChange={(e) => setIdLocal(parseInt(e.target.value))}
              className="border p-2 w-full rounded"
            />
          </div>
          <button type="submit" className="bg-red-500 text-white px-4 py-2 rounded w-full hover:bg-red-600">
            Eliminar Local
          </button>
        </form>
      </div>
    </div>
  );
};

export default ModalEliminarLocal;
