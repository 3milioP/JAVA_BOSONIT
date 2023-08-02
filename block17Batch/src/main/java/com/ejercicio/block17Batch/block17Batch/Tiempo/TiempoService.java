package com.ejercicio.block17Batch.block17Batch.Tiempo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

@Service
class TiempoService {
    private final JobLauncher jobLauncher;
    private final Job tiempoJob;

    public TiempoService(JobLauncher jobLauncher, Job tiempoJob) {
        this.jobLauncher = jobLauncher;
        this.tiempoJob = tiempoJob;
    }

    public void procesarTiempo() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(tiempoJob, jobParameters);
    }
}