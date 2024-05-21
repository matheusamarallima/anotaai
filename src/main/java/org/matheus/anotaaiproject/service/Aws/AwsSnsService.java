package org.matheus.anotaaiproject.service.Aws;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AwsSnsService {
    AmazonSNS sns;

    Topic catalogTopic;

    public AwsSnsService(AmazonSNS amazonSNS,@Qualifier("catalogEventsTopic") Topic catalogTopic){
        this.sns = amazonSNS;
        this.catalogTopic = catalogTopic;
    }

    public void publish(MessageDTO messageDTO){
        sns.publish(catalogTopic.getTopicArn(), messageDTO.message());
    }
}
