import { redirect } from 'next/navigation';

export default function HomePage() {
  // Redirige automáticamente al login
  redirect('/administradorpersonal/productos');

  return null;
}
