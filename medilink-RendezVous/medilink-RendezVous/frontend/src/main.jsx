import React, { useState } from 'react';
import { createRoot } from 'react-dom/client';

function App() {
  const [rendezvous, setRendezvous] = useState([]);
  const [nodeHealth, setNodeHealth] = useState('Not loaded');

  async function loadRendezvous() {
    const res = await fetch('http://localhost:8560/rendezvous');
    const data = await res.json();
    setRendezvous(data);
  }

  async function loadNodeHealth() {
    const res = await fetch('http://localhost:8090/node-health');
    const data = await res.json();
    setNodeHealth(JSON.stringify(data));
  }

  return (
    <main style={{ fontFamily: 'Arial', padding: 24 }}>
      <h1>Medilink Front Demo</h1>
      <p>Consomme Gateway + Node service.</p>
      <button onClick={loadRendezvous}>Load RendezVous</button>
      <button onClick={loadNodeHealth} style={{ marginLeft: 12 }}>Load Node Health</button>
      <h2>RendezVous</h2>
      <pre>{JSON.stringify(rendezvous, null, 2)}</pre>
      <h2>Node Health</h2>
      <pre>{nodeHealth}</pre>
    </main>
  );
}

createRoot(document.getElementById('root')).render(<App />);
