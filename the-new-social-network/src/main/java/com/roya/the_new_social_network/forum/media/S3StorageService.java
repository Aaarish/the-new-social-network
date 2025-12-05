package com.roya.the_new_social_network.forum.media;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class S3StorageService implements StorageService {
    private final S3Client s3Client;
    private final S3Presigner s3Presigner;

    @Value("${amazon.s3.bucket.name}")
    private String bucketName;

    @Value("${amazon.s3.url.expiration.minutes:10}")
    private long expirationMinutes;

    @Override
    public String generateMediaUploadUrl(String mediaId) {
        PutObjectPresignRequest putObjectPresignRequest = PutObjectPresignRequest.builder()
                .putObjectRequest(PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(mediaId)
                        .build())
                .signatureDuration(Duration.ofMinutes(expirationMinutes))
                .build();

        return s3Presigner.presignPutObject(putObjectPresignRequest).url().toString();
    }

    @Override
    public String generateMediaDownloadUrl(String mediaId) {
        GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
                .getObjectRequest(GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(mediaId)
                        .build())
                .signatureDuration(Duration.ofMinutes(1))
                .build();

        return s3Presigner.presignGetObject(getObjectPresignRequest).url().toString();
    }
}
