// Imports the Google Cloud client library
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;

public class QuickstartSample {
  public static void main(String... args) throws Exception {
	  // The name of the bucket to access
	 String bucketName = "ashrujit-gcp-storage";

	// The name of the remote file to download
	 String srcFilename = "api/bian/swaggers/current-account.yaml";

	// The path to which the file should be downloaded
	 Path destFilePath = Paths.get("C://CICD");

	// Instantiate a Google Cloud Storage client
	Storage storage = StorageOptions.getDefaultInstance().getService();

	// Get specific file from specified bucket
	Blob blob = storage.get(BlobId.of(bucketName, srcFilename));

	// Download file to specified path
	//blob.downloadTo(destFilePath);
	System.out.println("Size of blob  ::::: " + blob.getSize());
	
	byte[] contentInByte = blob.getContent();
	
	//String
	
	Swagger swagger = new SwaggerParser().parse(new String(blob.getContent()));
	
	System.out.println("Content   :::::    \n" + new String(contentInByte));
	
	
	System.out.println(swagger.getInfo().getDescription());
	
	String description = swagger.getInfo().getDescription();
	 
  }
}