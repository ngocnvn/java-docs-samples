// DO NOT EDIT! This is a generated sample ("Request",  "job_search_create_job_custom_attributes")
package com.google.cloud.examples.talent.v4beta1;

import com.google.cloud.talent.v4beta1.CreateJobRequest;
import com.google.cloud.talent.v4beta1.Job;
import com.google.cloud.talent.v4beta1.JobServiceClient;
import com.google.cloud.talent.v4beta1.TenantName;
import com.google.cloud.talent.v4beta1.TenantOrProjectName;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

// [START job_search_create_job_custom_attributes]
/*
 * Please include the following imports to run this sample.
 *
 * import com.google.cloud.talent.v4beta1.CreateJobRequest;
 * import com.google.cloud.talent.v4beta1.Job;
 * import com.google.cloud.talent.v4beta1.JobServiceClient;
 * import com.google.cloud.talent.v4beta1.TenantName;
 * import com.google.cloud.talent.v4beta1.TenantOrProjectName;
 */
public class JobSearchCreateJobCustomAttributes {
  /** Create Job with Custom Attributes */
  public static void sampleCreateJob(
      String projectId,
      String tenantId,
      String companyName,
      String requisitionId,
      String languageCode) {
    // [START job_search_create_job_custom_attributes_core]
    try (JobServiceClient jobServiceClient = JobServiceClient.create()) {
      // String projectId = "Your Google Cloud Project ID";
      // String tenantId = "Your Tenant ID (using tenancy is optional)";
      // String companyName = "Company name, e.g. projects/your-project/companies/company-id";
      // String requisitionId = "Job requisition ID, aka Posting ID. Unique per job.";
      // String languageCode = "en-US";
      TenantOrProjectName parent = TenantName.of(projectId, tenantId);
      Job job =
          Job.newBuilder()
              .setCompany(companyName)
              .setRequisitionId(requisitionId)
              .setLanguageCode(languageCode)
              .build();
      CreateJobRequest request =
          CreateJobRequest.newBuilder().setParent(parent.toString()).setJob(job).build();
      Job response = jobServiceClient.createJob(request);
      System.out.printf("Created job: %s\n", response.getName());
    } catch (Exception exception) {
      System.err.println("Failed to create the client due to: " + exception);
    }
    // [END job_search_create_job_custom_attributes_core]
  }

  public static void main(String[] args) throws Exception {
    Options options = new Options();
    options.addOption(
        Option.builder("").required(false).hasArg(true).longOpt("project_id").build());
    options.addOption(Option.builder("").required(false).hasArg(true).longOpt("tenant_id").build());
    options.addOption(
        Option.builder("").required(false).hasArg(true).longOpt("company_name").build());
    options.addOption(
        Option.builder("").required(false).hasArg(true).longOpt("requisition_id").build());
    options.addOption(
        Option.builder("").required(false).hasArg(true).longOpt("language_code").build());

    CommandLine cl = (new DefaultParser()).parse(options, args);
    String projectId = cl.getOptionValue("project_id", "Your Google Cloud Project ID");
    String tenantId = cl.getOptionValue("tenant_id", "Your Tenant ID (using tenancy is optional)");
    String companyName =
        cl.getOptionValue(
            "company_name", "Company name, e.g. projects/your-project/companies/company-id");
    String requisitionId =
        cl.getOptionValue("requisition_id", "Job requisition ID, aka Posting ID. Unique per job.");
    String languageCode = cl.getOptionValue("language_code", "en-US");

    sampleCreateJob(projectId, tenantId, companyName, requisitionId, languageCode);
  }
}
// FIXME: Insert here clean-up code.

// [END job_search_create_job_custom_attributes]