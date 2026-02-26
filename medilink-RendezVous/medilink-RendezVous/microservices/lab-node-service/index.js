const express = require('express');
const { Pool } = require('pg');

const app = express();
app.use(express.json());

const pool = new Pool({
  host: process.env.PGHOST || 'localhost',
  port: Number(process.env.PGPORT || 5432),
  user: process.env.PGUSER || 'medilink',
  password: process.env.PGPASSWORD || 'medilink',
  database: process.env.PGDATABASE || 'medilink'
});

app.get('/node-health', async (_req, res) => {
  try {
    const result = await pool.query('SELECT NOW() as now');
    res.json({ status: 'ok', dbTime: result.rows[0].now });
  } catch (e) {
    res.status(500).json({ status: 'error', message: e.message });
  }
});

app.post('/notes', async (req, res) => {
  const { title } = req.body;
  if (!title) return res.status(400).json({ error: 'title is required' });
  const r = await pool.query('INSERT INTO notes(title) VALUES($1) RETURNING *', [title]);
  res.status(201).json(r.rows[0]);
});

app.get('/notes', async (_req, res) => {
  const r = await pool.query('SELECT id, title FROM notes ORDER BY id DESC');
  res.json(r.rows);
});

const port = process.env.PORT || 8090;
app.listen(port, () => console.log(`lab-node-service listening on ${port}`));
