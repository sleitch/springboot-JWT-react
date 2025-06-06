import React from 'react';
import { Button, Container, Box, Grid, Paper, Typography } from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';

export default function HomePage() {
  return (
    <>
      {/* Custom Menu Bar */}
      <Box
        sx={{
          width: '100%',
          bgcolor: 'background.paper',
          borderBottom: 1,
          borderColor: 'divider',
          py: 1,
          px: 2,
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'space-between',
          position: 'sticky',
          top: 0,
          zIndex: 100,
        }}
      >
        <Typography variant="h6" sx={{ fontWeight: 700, color: 'primary.main' }}>
          User 1
        </Typography>
        <Box>
          <Button
            component={RouterLink}
            to="/login"
            size="small"
            sx={{ mx: 1, textTransform: 'none' }}
            color="primary"
          >
            Login
          </Button>
          <Button
            component={RouterLink}
            to="/register"
            size="small"
            sx={{ mx: 1, textTransform: 'none' }}
            color="primary"
          >
            Register
          </Button>
         
        </Box>
      </Box>

      <Container maxWidth="md" sx={{ mt: 6 }}>
        <Box sx={{ textAlign: 'center', mb: 4 }}>
          <Typography variant="h3" gutterBottom>
            Welcome to User One Eve
          </Typography>
          <Typography variant="h6" color="text.secondary">
            Your all-in-one platform for user management, secure authentication, and profile customization.
          </Typography>
        </Box>
        <Grid container spacing={4}>
          <Grid item xs={12} md={6}>
            <Paper elevation={3} sx={{ p: 3 }}>
              <Typography variant="h5" gutterBottom>
                🚀 Quick Start
              </Typography>
              <Typography component="div">
                <ul>
                  <li>Register a new account in seconds</li>
                  <li>Login securely with your credentials</li>
                  <li>Reset your password anytime</li>
                </ul>
              </Typography>
            </Paper>
          </Grid>
          <Grid item xs={12} md={6}>
            <Paper elevation={3} sx={{ p: 3 }}>
              <Typography variant="h5" gutterBottom>
                🎨 Features
              </Typography>
              <Typography component="div">
                <ul>
                  <li>Modern, responsive UI with Material-UI</li>
                  <li>Profile management and customization</li>
                  <li>Secure backend powered by Spring Boot</li>
                </ul>
              </Typography>
            </Paper>
          </Grid>
        </Grid>
        <Box sx={{ mt: 6, textAlign: 'center', color: 'text.secondary' }}>
          <Typography variant="body2">
            Need help? Visit our <RouterLink to="/help">Help Center</RouterLink> or contact support.
          </Typography>
        </Box>
      </Container>
    </>
  );
}