package maville.equipe27.controllers;

import com.google.gson.Gson;
import maville.equipe27.helpers.ConnectedIntervenant;
import maville.equipe27.helpers.NotifcationEmitter;
import maville.equipe27.helpers.ProjectDataStore;
import maville.equipe27.helpers.RequeteTravailDataStore;
import maville.equipe27.models.Intervenant;
import maville.equipe27.models.Projet;
import maville.equipe27.models.RequeteTravail;
import maville.equipe27.views.IntervenantView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IntervenantControllerTests {

    private IntervenantController intervenantController;

    @Mock
    private IntervenantView mockView;

    @Mock
    private RequeteTravailDataStore mockRequeteTravailDataStore;

    @Mock
    private ProjectDataStore mockProjectDataStore;

    @Mock
    private NotifcationEmitter mockNotifcationEmitter;

    @Mock
    private Intervenant mockIntervenant;

    @Mock
    private Projet mockProjet;

    @Mock
    private RequeteTravail mockRequeteTravail;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        intervenantController = new IntervenantController(mockView);
        intervenantController.requeteTravailDataStore = mockRequeteTravailDataStore;
        intervenantController.projectDataStore = mockProjectDataStore;
        intervenantController.notifcationEmitter = mockNotifcationEmitter;
    }

    @Test
    void testCreateNewProject_Success() {
        when(mockProjectDataStore.saveProject(mockProjet)).thenReturn(true);
        when(mockNotifcationEmitter.emit(eq(mockProjet), anyString())).thenReturn(true);

        boolean result = intervenantController.createNewProject(mockProjet);

        assertTrue(result, "createNewProject should return true if the project is saved and notification is sent.");
        verify(mockProjectDataStore).saveProject(mockProjet);
        verify(mockNotifcationEmitter).emit(eq(mockProjet), contains("Nouveau projet"));
    }

    @Test
    void testCreateNewProject_Failure() {
        when(mockProjectDataStore.saveProject(mockProjet)).thenReturn(false);

        boolean result = intervenantController.createNewProject(mockProjet);

        assertFalse(result, "createNewProject should return false if the project fails to save.");
        verify(mockProjectDataStore).saveProject(mockProjet);
        verify(mockNotifcationEmitter, never()).emit(any(), anyString());
    }

    @Test
    void testGetUserProjects() {
        ArrayList<Projet> projects = new ArrayList<>();
        when(mockProjectDataStore.getUserProjects()).thenReturn(projects);

        List<Projet> result = intervenantController.getUserProjects();

        assertEquals(projects, result, "getUserProjects should return the correct project list.");
        verify(mockProjectDataStore).getUserProjects();
    }

    @Test
    void testUpdateProject_Success() {
        when(mockProjectDataStore.updateProject(mockProjet, "New Title")).thenReturn(true);
        when(mockNotifcationEmitter.emit(eq(mockProjet), anyString())).thenReturn(true);

        boolean result = intervenantController.updateProject(mockProjet, "New Title");

        assertTrue(result, "updateProject should return true if update and notification succeed.");
        verify(mockProjectDataStore).updateProject(mockProjet, "New Title");
        verify(mockNotifcationEmitter).emit(eq(mockProjet), contains("Mise à jour"));
    }

    @Test
    void testSoumettreCandidatureRequete() {
        when(mockRequeteTravailDataStore.addCandidate(any(), anyString())).thenReturn(true);
        ConnectedIntervenant mockConnected = mock(ConnectedIntervenant.class);
        Intervenant mockIntervenant = mock(Intervenant.class);
        when(mockConnected.getIntervenant()).thenReturn(mockIntervenant);
        when(mockIntervenant.getEmail()).thenReturn("test@example.com");

        ConnectedIntervenant.getInstance().setIntervenant(mockIntervenant); // Set the mock singleton instance
        boolean result = intervenantController.soumettreCandiatureRequete(mockRequeteTravail);

        assertTrue(result, "soumettreCandiatureRequete should return true if candidate is added.");
        verify(mockRequeteTravailDataStore).addCandidate(eq(mockRequeteTravail), eq("test@example.com"));
    }

    @Test
    void testConsulterRequetes() {
        List<RequeteTravail> mockRequetes = new ArrayList<>();
        when(mockRequeteTravailDataStore.getRequetes()).thenReturn(mockRequetes);

        List<RequeteTravail> result = intervenantController.consulterRequetes();

        assertEquals(mockRequetes, result, "consulterRequetes should return the correct list of requests.");
        verify(mockRequeteTravailDataStore).getRequetes();
    }
}
